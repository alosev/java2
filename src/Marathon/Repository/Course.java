package Marathon.Repository;

import Marathon.Competitor.Competitor;
import Marathon.Obstacle.Obstacle;

public class Course {
    private final Obstacle[] course;

    public Course(Obstacle[] course) {
        this.course = course;
    }

    public void doIt(Team team){
        for (Competitor c : team.getTeamCompetitors()) {
            for (Obstacle o : course) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }
    }
}
