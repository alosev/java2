package Marathon.Repository;

import Marathon.Competitor.Competitor;

public class Team {
    private final Competitor[] competitors;
    private final String teamName;

    public Team(String teamName, Competitor[] competitors) {
        this.teamName = teamName;
        this.competitors = competitors;
    }

    public void getAllTeamInfo(){
        System.out.println("Полный состав команды: " + teamName);

        for (Competitor item: competitors) {
            item.info();
        }
    }

    public void getOnDistanceTeamInfo(){
        System.out.println("Прошедшие дистанцию участники команды: " + teamName);

        for (Competitor item: competitors) {
            if(item.isOnDistance()){
                item.info();
            }
        }
    }

    public Competitor[] getTeamCompetitors(){
        return competitors;
    }
}
