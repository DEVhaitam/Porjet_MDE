package com.example.helpers;

import com.example.dto.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class InputReader {
    public String readInput(){
        Input input = new Input();
        System.out.println("Saisir le nom du pipeline:");
        Scanner sPipe = new Scanner(System.in);
        input.pipelineName =  sPipe.next();
        Scanner sJobs;
        Job job;
        while(true){
            System.out.println("Saisir le nom du job ou Done pour passer:");
            sJobs = new Scanner(System.in);
            String ss = sJobs.next();
            if(ss.equals("Done")) break;
            else{
                job = new Job();
                job.name = ss;
                Scanner sEnv ;
                while(true){
                    System.out.println("Voulez vous introduire une variable d'env?");
                    sEnv = new Scanner(System.in);
                    if(sEnv.next().equals("Non")) break;
                    else{
                        Scanner sKeyValue = new Scanner(System.in);
                        EnvVariable env = new EnvVariable(sKeyValue.next(),sKeyValue.next());
                        job.envVariables.add(env);
                    }

                }
                input.jobs.add(job);
            }
        }

        Scanner sEvents;
        Event event;
        while(true){
            System.out.println("Saisir le nom de l'evenement ou Done pour passer:");
            sEvents = new Scanner(System.in);
            String sss = sEvents.next();
            if(sss.equals("Done")) break;
            else{
                event = new Event();
                event.name = sss;
                Scanner sBra ;
                while(true){
                    System.out.println("Saisir le nom de du branch ou Done pour passer:");
                    sBra = new Scanner(System.in);
                    String name = sBra.next();
                    if(name.equals("Done")) break;
                    else{
                        Branch b = new Branch(name);
                        event.branches.add(b);
                    }

                }
                input.events.add(event);
            }
        }

        String output = new String("<?nsuri input?>\n<Input pipelineName=\""+input.pipelineName+"\">\n");

        output+= "<jobs>\n";
        for(Job j :input.jobs ){
            output+="<Job name=\""+j.name+"\">\n";

            if(j.envVariables.size()>0){
                output+="      <envVariables>\n";
                for(EnvVariable v : j.envVariables ){
                    output+="            <EnvVariable key=\""+v.key+"\" value=\""+v.value+"\"/>\n";
                }
                output+="      </envVariables>\n";
            }
            output+="</Job>\n";
        }
        output+= "</jobs>\n";

        output+= "<events>\n";
        for(Event e :input.events ){
            output+="<Event eventType=\""+e.name+"\">\n";

            if(e.branches.size()>0){
                for(Branch b : e.branches ) {
                    output += "     <branch name=\"" + b.name + "\"/>\n";
                }
            }
            output+="</Event>\n";
        }
        output+= "</events>\n";
        output+= "</Input>\n";
        return output;
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
