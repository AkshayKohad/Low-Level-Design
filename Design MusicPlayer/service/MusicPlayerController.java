package service;

import command.Command;

public class MusicPlayerController {

    public void execute(Command command) {
        command.execute();
    }
}