package Marathon;

import Marathon.Competitor.*;
import Marathon.Obstacle.*;
import Marathon.Repository.*;

//
// Лосев Алексей
//

public class Main {
    public static void main(String[] args) {
        Run();
    }

    private static void Run(){
        Competitor[] competitors = {new Human( "Куклачев" ), new Cat( "Барсик" ), new Cat( "Пирожок" )};
        Team team1 = new Team( "Куклачев и Ко", competitors );
        team1.getAllTeamInfo();

        Obstacle[] obstacles = {new Cross( 80 ), new Wall( 2 ), new Wall( 1 ), new Cross( 120 )};
        Course course = new Course( obstacles );
        course.doIt( team1 );

        team1.getOnDistanceTeamInfo();
    }
}
