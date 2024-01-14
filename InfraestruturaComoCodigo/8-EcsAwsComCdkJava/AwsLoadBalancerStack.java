package com.myorg;

import software.amazon.awscdk.*;
import software.amazon.awscdk.services.applicationautoscaling.EnableScalingProps;
import software.amazon.awscdk.services.ecr.IRepository;
import software.amazon.awscdk.services.ecr.Repository;
import software.amazon.awscdk.services.ecs.*;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedTaskImageOptions;
import software.amazon.awscdk.services.logs.LogGroup;
import software.constructs.Construct;

import java.util.HashMap;
import java.util.Map;

public class AwsLoadBalancerStack extends Stack {
    public AwsLoadBalancerStack(final Construct scope, final String id, final Cluster cluster) {
        this(scope, id, null, cluster);
    }

    public AwsLoadBalancerStack(final Construct scope, final String id, final StackProps props, final Cluster cluster) {
        super(scope, id, props);

        Map<String, String> autenticacao = new HashMap<>();
        autenticacao.put("SPRING_DATASOURCE_URL", "jdbc:mysql://" + Fn.importValue("pedidos-db-endpoint")
            + ":3306/microservice-x?createDatabaseIfNotExists=true");
        autenticacao.put("SPRING_DATASOURCE_USERNAME", "admin");
        autenticacao.put("SPRING_DATASOURCE_PASSWORD", Fn.importValue("pedidos-db-senha"));

        IRepository repository = Repository.fromRepositoryName(this, "repositorio", "img-microservice-ms");

        ApplicationLoadBalancedFargateService awsService = ApplicationLoadBalancedFargateService.Builder.create(this, "MicroservicesLB")
                .serviceName("microservice-ms")
                .cluster(cluster)
                .cpu(512)
                .desiredCount(1)
                .listenerPort(8080)
                .assignPublicIp(true)
                .taskImageOptions(
                        ApplicationLoadBalancedTaskImageOptions.builder()
                                .image(ContainerImage.fromEcrRepository(repository))
                                .containerPort(8080)
                                .containerName("app")
                                .environment(autenticacao)
                                .logDriver(LogDriver.awsLogs(AwsLogDriverProps.builder()
                                        .logGroup(LogGroup.Builder.create(this, "MicroserviceMsLogGroup")
                                                .logGroupName("ServiceMsLog")
                                                .removalPolicy(RemovalPolicy.DESTROY)
                                                .build())
                                        .streamPrefix("ServiceMs")
                                        .build()))
                                .build())
                .memoryLimitMiB(1024)
                .publicLoadBalancer(true)
                .build();

        ScalableTaskCount scalableTarget = awsService.getService().autoScaleTaskCount(EnableScalingProps.builder()
                .minCapacity(1)
                .maxCapacity(10)
                .build());

        scalableTarget.scaleOnCpuUtilization("CpuScaling", CpuUtilizationScalingProps.builder()
                .targetUtilizationPercent(70)
                .scaleInCooldown(Duration.minutes(3))
                .scaleOutCooldown(Duration.minutes(2))
                .build());

        scalableTarget.scaleOnMemoryUtilization("MemoryScaling", MemoryUtilizationScalingProps.builder()
                .targetUtilizationPercent(65)
                .scaleInCooldown(Duration.minutes(3))
                .scaleOutCooldown(Duration.minutes(2))
                .build());
    }

}
