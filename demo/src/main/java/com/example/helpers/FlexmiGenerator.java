package com.example.helpers;

import com.example.dto.*;

public class FlexmiGenerator {
    public static String generate(Input input){
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
}
