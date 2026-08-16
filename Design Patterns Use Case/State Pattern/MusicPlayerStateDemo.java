interface PlayerState{
    void play(MusicPlayer player);
    void pause(MusicPlayer player);
    void stop(MusicPlayer player);
}

class StoppedState implements PlayerState{
    @Override
    public void play(MusicPlayer player){
        System.out.println("Player was currently in Stopped going to play state");
        player.setState(new PlayingState());
    }

    @Override
    public void pause(MusicPlayer player){
        System.out.println("Cannot pause: player is stopped");
    }

    @Override
    public void stop(MusicPlayer player){
        System.out.println("Player is already in stop state");
    }
}

class PausedState implements PlayerState{
    @Override
    public void play(MusicPlayer player){
        System.out.println("Player was currently in Paused going to play state");
        player.setState(new PlayingState());
    }

    @Override
    public void pause(MusicPlayer player){
        System.out.println("Player is already in paused state");
    }

    @Override
    public void stop(MusicPlayer player){
        System.out.println("player was currently in Paused going to stop state");
        player.setState(new StoppedState());
    }
}


class PlayingState implements PlayerState{
    @Override
    public void play(MusicPlayer player){
        System.out.println("Player is already in play state");
    }

    @Override
    public void pause(MusicPlayer player){
        System.out.println("Player is in play state going to pause state");
        player.setState(new PausedState());
    }

    @Override
    public void stop(MusicPlayer player){
        System.out.println("Player is in play state going to stop state");
        player.setState(new StoppedState());
    }
}

class MusicPlayer{
    PlayerState curState = new StoppedState();

    public void playMusic(){
        curState.play(this);
    }

    public void pauseMusic(){
        curState.pause(this);
    }

    public void stopMusic(){
        curState.stop(this);
    }

    public void setState(PlayerState newState){
        this.curState = newState;
    }
}

public class MusicPlayerStateDemo{
    public static void main(String[] args){
        MusicPlayer player = new MusicPlayer();
        player.playMusic();
        player.pauseMusic();
        player.playMusic();
        player.stopMusic();
        player.pauseMusic();
    }
}
