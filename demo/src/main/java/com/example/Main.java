package com.example;

import com.example.helpers.InputReader;
import com.example.transformations.Model2Model;
import com.example.transformations.Model2Text;

public class Main {
    public static void main(String[] args) throws Exception {

        InputReader inputReader = new InputReader();
        inputReader.writeToFile("src/main/resources/test.flexmi",inputReader.readInput());
        //System.out.println(inputReader.readInput());
        Model2Model m = new Model2Model();
        Model2Text mm = new Model2Text();
        System.out.println(mm.m2t(m.pipeLineModel()));
    }
}