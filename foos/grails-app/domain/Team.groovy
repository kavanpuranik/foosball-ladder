class Team {
	Player playerOne
	Player playerTwo
	String name
	Integer rank
	Calendar lastPlayed
	String toString() { name + " - " + playerOne.toString() + " / " + playerTwo.toString() }
    
    def eachPlayer(closure) {
    	closure(playerOne)
    	closure(playerTwo)
    }
    
    static constraints = {
        lastPlayed(nullable: true)
    }
    
    static getTeam(player1, player2) {
    	def team = Team.findByPlayerOneAndPlayerTwo(player1, player2)
    	if (!team) {
    		team = Team.findByPlayerOneAndPlayerTwo(player2, player1)
    	}
    	if (!team) {
    		team = new Team(playerOne:player1, playerTwo:player2, name:"${player1.name} & ${player2.name}")
    	}
    	
    	return team
    }
}