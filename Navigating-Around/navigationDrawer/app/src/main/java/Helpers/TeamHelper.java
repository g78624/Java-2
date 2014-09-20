//Kyle Kauck

package Helpers;

import java.io.Serializable;

public class TeamHelper implements Serializable {

    private String mTeamName;
    private String mFirstPlayer;
    private String mSecondPlayer;
    private String mThirdPlayer;
    private String mFourthPlayer;
    private String mFifthPlayer;
    private String mSixthPlayer;
    private String mSeventhPlayer;
    private String mEighthPlayer;
    private String mNinthPlayer;

    public TeamHelper(){

        mTeamName = "";
        mFirstPlayer = "";
        mSecondPlayer = "";
        mThirdPlayer = "";
        mFourthPlayer = "";
        mFifthPlayer = "";
        mSixthPlayer = "";
        mSeventhPlayer = "";
        mEighthPlayer = "";
        mNinthPlayer = "";

    }

    public TeamHelper (String _team, String _one, String _two, String _three, String _four, String _five, String _six, String _seven, String _eight, String _nine){

        mTeamName = _team;
        mFirstPlayer = _one;
        mSecondPlayer = _two;
        mThirdPlayer = _three;
        mFourthPlayer = _four;
        mFifthPlayer = _five;
        mSixthPlayer = _six;
        mSeventhPlayer = _seven;
        mEighthPlayer = _eight;
        mNinthPlayer = _nine;

    }

    public String getTeam(){

        return mTeamName;

    }

    public String getOne(){

        return mFirstPlayer;

    }

    public String getTwo(){

        return mSecondPlayer;

    }

    public String getThree(){

        return mThirdPlayer;

    }

    public String getFour(){

        return mFourthPlayer;

    }

    public String getFive(){

        return mFifthPlayer;

    }

    public String getSix(){

        return mSixthPlayer;

    }

    public String getSeven(){

        return mSeventhPlayer;

    }

    public String getEight(){

        return mEighthPlayer;

    }

    public String getNine(){

        return mNinthPlayer;

    }

}
