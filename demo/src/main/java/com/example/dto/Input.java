package com.example.dto;

import java.util.ArrayList;
import java.util.List;

public class Input {
    public String pipelineName;
    public List<Job> jobs = new ArrayList<>();
    public List<Event> events = new ArrayList<>();
}
