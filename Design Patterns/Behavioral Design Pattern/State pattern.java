// State interface
interface MusicPlayerState {
    void pressPlayPause(MusicPlayer player);
}

// Concrete State: Playing
class PlayingState implements MusicPlayerState {

    @Override
    public void pressPlayPause(MusicPlayer player) {
        System.out.println("Music is playing. Pausing now...");
        player.setState(new PausedState());
    }
}

// Concrete State: Paused
class PausedState implements MusicPlayerState {

    @Override
    public void pressPlayPause(MusicPlayer player) {
        System.out.println("Music is paused. Playing now...");
        player.setState(new PlayingState());
    }
}

// Context
class MusicPlayer {
    private MusicPlayerState currentState;

    public MusicPlayer() {
        currentState = new PausedState();
    }

    public void setState(MusicPlayerState state) {
        currentState = state;
    }

    public void pressPlayPause() {
        currentState.pressPlayPause(this);
    }
}

// Client
public class StatePatternDemo {
    public static void main(String[] args) {

        MusicPlayer player = new MusicPlayer();

        player.pressPlayPause(); // Paused -> Playing
        player.pressPlayPause(); // Playing -> Paused
        player.pressPlayPause(); // Paused -> Playing
    }
}
Output
Music is paused. Playing now...
Music is playing. Pausing now...
Music is paused. Playing now...
