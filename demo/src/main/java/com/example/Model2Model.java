package com.example;

import java.io.File;
import java.io.IOException;

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.etl.EtlModule;

public class Model2Model {
    //private String pipeline = FileReader.readFile("demo/src/main/resources/pipeline.emf");
    ///private String  input = FileReader.readFile(pipeline);
    //private String transformation = FileReader.readFile("demo/src/main/resources/input2pipeline.etl");


    public InMemoryEmfModel pipeLineModel() throws Exception{
        System.out.println("modeltomodeltr");
        EtlModule module = new EtlModule();
        module.parse(new File("./src/main/resources/input2pipeline.etl"));
        if(!module.getParseProblems().isEmpty()){
            throw new RuntimeException(module.getParseProblems().get(0).toString());
        }
        module.getContext().setOutputStream(System.out);
        return runTransformation(
                module
        );
    }

    private InMemoryEmfModel runTransformation(EtlModule module)
            throws Exception {
        System.out.println("runTransformation");
        InMemoryEmfModel inputModel = ModelLoader.getInMemoryFlexmiModel(new FileReader().readFile("src/main/resources/input.flexmi"),new FileReader().readFile(".\\src\\main\\resources\\input.emf") );
        inputModel.setName("Source");
        InMemoryEmfModel pipelineModel = ModelLoader.getBlankInMemoryModel(new FileReader().readFile("src/main/resources/pipeline.emf"));
        pipelineModel.setName("Target");
        module.getContext().getModelRepository().addModel(inputModel);
        module.getContext().getModelRepository().addModel(pipelineModel);
        module.execute();

        return pipelineModel;
    }
}
