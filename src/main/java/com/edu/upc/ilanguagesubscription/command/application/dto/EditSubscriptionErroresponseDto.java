package com.edu.upc.ilanguagesubscription.command.application.dto;

public class EditSubscriptionErroresponseDto {
    private String message;

    public EditSubscriptionErroresponseDto(){
        this.message = "Error Edition Subcription";
    }
    public String getMessage(){
        return message;
    }
}
