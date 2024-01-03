package com.example;

import org.eclipse.epsilon.etl.parse.EtlUnparser;

public class Main {
    public static void main(String[] args) throws Exception {
        Model2Model m = new Model2Model();
        Model2Text mm = new Model2Text();
        System.out.println(mm.m2t(m.pipeLineModel()));
    }
}