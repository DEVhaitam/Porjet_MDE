package com.example;

import com.example.dto.*;
import com.example.dto.Event;
import com.example.helpers.FlexmiGenerator;
import com.example.helpers.InputReader;
import com.example.transformations.Model2Model;
import com.example.transformations.Model2Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PipelineFrame extends JFrame {
    private StringBuilder pipelineConfiguration = new StringBuilder();
    private Input input = new Input();
    public PipelineFrame() {
        // Set the frame title
        setTitle("Pipeline Configuration Frame");

        // Create a JPanel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Pipeline Name
        JLabel pipelineLabel = new JLabel("Pipeline Name:");
        JTextField pipelineTextField = new JTextField(20);
        panel.add(createPanel(pipelineLabel, pipelineTextField));

        // Jobs Section
        JLabel jobsLabel = new JLabel("Jobs:");
        panel.add(createPanel(jobsLabel));

        JButton addJobButton = new JButton("Add Job");
        addJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Job job = new Job();
                // Show dialog for adding a job
                String[] jobTypes = {"build", "test", "deploy"};
                JComboBox<String> jobTypeComboBox = new JComboBox<>(jobTypes);
                JButton addEnvVarButton = new JButton("Add Environment Variable");

                addEnvVarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        EnvVariable envVariable = new EnvVariable();
                        // Show dialog for adding environment variables
                        JTextField keyTextField = new JTextField(10);
                        JTextField valueTextField = new JTextField(10);

                        JPanel envVarPanel = createPanel(new JLabel("Key:"), keyTextField, new JLabel("Value:"), valueTextField);
                        int result = JOptionPane.showConfirmDialog(null, envVarPanel, "Add Environment Variable", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            // Handle the data (you can save it or perform any action)
                            String key = keyTextField.getText();
                            String value = valueTextField.getText();
                            envVariable.key = key;
                            envVariable.value = value;
                            job.envVariables.add(envVariable);
                        }
                    }
                });

                JPanel jobPanel = createPanel(new JLabel("Name:"), jobTypeComboBox, addEnvVarButton);
                int result = JOptionPane.showConfirmDialog(null, jobPanel, "Add Job", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    // Handle the data (you can save it or perform any action)
                    String jobType = (String) jobTypeComboBox.getSelectedItem();
                    job.name = jobType;
                    input.jobs.add(job);
                }
            }
        });

        panel.add(createPanel(addJobButton));

        // Events Section
        JLabel eventsLabel = new JLabel("Events:");
        panel.add(createPanel(eventsLabel));

        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                com.example.dto.Event event = new Event();
                // Show dialog for adding an event
                String[] eventTypes = {"push", "pull-request", "schedule"};
                JComboBox<String> eventTypeComboBox = new JComboBox<>(eventTypes);
                JButton addBranchButton = new JButton("Add Branch");

                addBranchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Branch branch = new Branch();
                        // Show dialog for adding a branch
                        JTextField branchTextField = new JTextField(10);
                        JPanel branchPanel = createPanel(new JLabel("Branch Name:"), branchTextField);
                        int result = JOptionPane.showConfirmDialog(null, branchPanel, "Add Branch", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            // Handle the data (you can save it or perform any action)
                            String branchName = branchTextField.getText();
                            branch.name = branchName;
                            event.branches.add(branch);
                        }
                    }
                });

                JPanel eventPanel = createPanel(new JLabel("Type:"), eventTypeComboBox, addBranchButton);
                int result = JOptionPane.showConfirmDialog(null, eventPanel, "Add Event", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    // Handle the data (you can save it or perform any action)
                    String eventType = (String) eventTypeComboBox.getSelectedItem();
                    event.name = eventType;
                    input.events.add(event);
                }
            }
        });

        panel.add(createPanel(addEventButton));

        // Generate Pipeline Button
        JButton generatePipelineButton = new JButton("Generate Pipeline");
        generatePipelineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.pipelineName = pipelineTextField.getText();
                InputReader inputReader = new InputReader();
                inputReader.writeToFile("src/main/resources/test.flexmi", FlexmiGenerator.generate(input));
                Model2Model m = new Model2Model();
                Model2Text mm = new Model2Text();
                try {
                    mm.m2t(m.pipeLineModel());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                // Generate pipeline configuration based on entered data


                // You may append more details to pipelineConfiguration based on your requirements

                // Display the generated pipeline configuration
                JOptionPane.showMessageDialog(null, "Generated Pipeline Configuration:\n\n");
            }
        });

        panel.add(createPanel(generatePipelineButton));

        // Add the panel to the frame
        add(panel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createPanel(Component... components) {
        JPanel panel = new JPanel();
        for (Component component : components) {
            panel.add(component);
        }
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PipelineFrame();
            }
        });
    }
}
