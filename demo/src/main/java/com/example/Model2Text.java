package com.example;

import org.eclipse.epsilon.egl.EglTemplateFactoryModuleAdapter;
import org.eclipse.epsilon.egl.IEglModule;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;

import java.io.File;

public class Model2Text {

    public String m2t(InMemoryEmfModel model) throws Exception {
        System.out.println("modeltotext");
        IEglModule module = (IEglModule) new EglTemplateFactoryModuleAdapter();
        module.parse(new FileReader().readFile("src/main/resources/m2t.egl"), new File("/program.egl"));
        if(!module.getParseProblems().isEmpty())
        {
            throw new RuntimeException(module.getParseProblems().get(0).toString());
        }
        module.getContext().getModelRepository().addModel(model);
        return module.execute() + "";
    }
}
