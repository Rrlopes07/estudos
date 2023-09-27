package br.com.dio.desafio.dominio;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Curso curso1 = new Curso();
        curso1.setTitulo("curso java");
        curso1.setDescricao("descrição curso java");
        curso1.setCargaHoraria(8);

        Curso curso2 = new Curso();
        curso2.setTitulo("curso python");
        curso2.setDescricao("descrição curso python");
        curso2.setCargaHoraria(4);

        Conteudo conteudo = new Curso();

        Mentoria mentoria = new Mentoria();
        mentoria.setTitulo("mentoria de java");
        mentoria.setDescricao("descrição mentoria java");
        mentoria.setData(LocalDate.now());

//        System.out.println(curso1);
//        System.out.println(curso2);
//        System.out.println(mentoria);

        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setNome("Bootcamp Java Developer");
        bootcamp.setDescricao("Descrição Bootcamp Java Developer");
        bootcamp.getConteudos().add(curso1);
        bootcamp.getConteudos().add(curso2);
        bootcamp.getConteudos().add(mentoria);

        Dev devRaphael = new Dev();
        devRaphael.setNome("Raphael");
        devRaphael.inscrever(bootcamp);
        System.out.println("Conteúdos inscritos Raphael:" + devRaphael.getConteudosInscritos());
        devRaphael.progredir();
        System.out.println("------");
        System.out.println("Conteúdos concluidos Raphael:" + devRaphael.getConteudosConcluidos());
        System.out.println("Conteúdos inscritos Raphael:" + devRaphael.getConteudosInscritos());
        devRaphael.progredir();
        System.out.println("XP:" + devRaphael.calcularTotalXp());

        System.out.println("------");

        Dev devMaiara = new Dev();
        devMaiara.setNome("Maiara");
        devMaiara.inscrever(bootcamp);
        System.out.println("Conteúdos inscritos Maiara:" + devMaiara.getConteudosInscritos());
        devMaiara.progredir();
        devMaiara.progredir();
        devMaiara.progredir();
        System.out.println("------");
        System.out.println("Conteúdos concluidos Maiara:" + devMaiara.getConteudosConcluidos());
        System.out.println("Conteúdos inscritos Maiara:" + devMaiara.getConteudosInscritos());
        System.out.println("XP:" + devMaiara.calcularTotalXp());
    }
}