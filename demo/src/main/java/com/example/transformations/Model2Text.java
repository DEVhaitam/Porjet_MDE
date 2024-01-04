package com.example.transformations;

import com.example.helpers.FileReader;
import org.eclipse.epsilon.egl.EglTemplateFactoryModuleAdapter;
import org.eclipse.epsilon.egl.IEglModule;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        writeToFile("src/main/resources/output.yml",module.execute() + "");
        return module.execute() + "";
    }

    public void writeToFile(String filePath, String content){
        try {
            // Check if the file already exists, create if not
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("File created: " + filePath);
            }

            // Create a BufferedWriter with FileWriter
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));

            // Write content to the file
            writer.write(content);

            // Close the writer to release resources
            writer.close();

            System.out.println("Content has been written to the file.");

        } catch (FileAlreadyExistsException e) {
            System.out.println("File already exists: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
