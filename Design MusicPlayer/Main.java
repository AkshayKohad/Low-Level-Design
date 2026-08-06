import command.*;
import model.*;
import player.*;
import service.*;

public class Main {

    public static void main(String[] args) {

        Song s1 = new Song("1", "Believer", "Imagine Dragons", 210);
        Song s2 = new Song("2", "Shape of You", "Ed Sheeran", 240);

        MusicPlayer player = new MusicPlayer();

        MusicPlayerController controller = new MusicPlayerController();

        controller.execute(new PlayCommand(player, s1));

        controller.execute(new PauseCommand(player));

        controller.execute(new ResumeCommand(player));

        controller.execute(new AddToQueueCommand(player, s2));

        controller.execute(new NextCommand(player));

        controller.execute(new ToggleShuffleCommand(player));

        controller.execute(new ToggleLoopCommand(player, LoopMode.ALL));
    }
}