package br.com.team.appx.convinience.model;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Notification {
    @NotEmpty
    public String title;
    @NotEmpty
    public String body;
}
