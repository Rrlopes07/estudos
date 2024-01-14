package com.myorg;

import software.amazon.awscdk.App;

public class AwsInfraApp {

    public static void main(final String[] args) {
        App app = new App();

        AwsVpcStack vpc = new AwsVpcStack(app, "vpc");

        AwsClusterStack cluster = new AwsClusterStack(app, "Cluster", vpc.getVpc());
        cluster.addDependency(vpc);

        AwsRdsStack dbStack = new AwsRdsStack(app, "microservice-db", vpc.getVpc());
        dbStack.addDependency(vpc);

        AwsLoadBalancerStack stack = new AwsLoadBalancerStack(app, "Service", cluster.getCluster());
        stack.addDependency(cluster);
        stack.addDependency(dbStack);

        app.synth();
    }

}
