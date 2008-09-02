            
class TeamController {
    def index = {
		def list = new LinkedList(Team.listOrderByRank())
        reconcileTeamRanking(list)
    	reconcilePlayerRating()
    	redirect(action:list,params:params)
	}

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
    	def list = new LinkedList(Team.listOrderByRank())
		reconcileTeamRanking(list)
    	reconcilePlayerRating()
        [ teamList: Team.listOrderByRank(), playerList: Player.listOrderByRank() ]
    }

    def show = {
        def team = Team.get( params.id )

        if(!team) {
            flash.message = "Team not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ team : team ] }
    }

    def delete = {
        def team = Team.get( params.id )
        if(team) {
            
            def list = new LinkedList(Team.listOrderByRank())
    		list.remove(team)
            team.delete()
            
            reconcileTeamRanking(list)
            reconcilePlayerRating()
        
            flash.message = "Team ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Team not found with id ${params.id}"
            redirect(action:list)
        }
    }
    
    
    private reconcileTeamRanking(teamList){
        teamList.each { each ->
            each.rank = teamList.indexOf(each) + 1
            each.save()
        }
    } 

    private reconcilePlayerRating(){
        def players = Player.listOrderByRank()
        
        players.each { player ->
        	player.rating = PlayerRatingStrategyController.playerRatingStrategy.computePlayerRating(player)
            println "Player: ${player.name} computed average: ${player.rating}"
        }
        
        println "before list: ${players}"
        players = players.sort { each1, each2 -> each1.rating <=> each2.rating }
        println "after list: ${players}"
        
        players.eachWithIndex { player, index ->
        	player.rank = index + 1
        	if (index > 0 && players[index-1].rating == player.rating) player.rank = players[index-1].rank
            println "Player: ${player.name} updated rank: ${player.rank}"
            player.save()
        }
    }
    
       
    
    def edit = {
        def team = Team.get( params.id )

        if(!team) {
            flash.message = "Team not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ team : team ]
        }
    }

    def update = {
        def team = Team.get( params.id )
        if(team) {
            team.properties = params
            if(!team.hasErrors() && team.save()) {
                flash.message = "Team ${params.id} updated"
                redirect(action:show,id:team.id)
            }
            else {
                render(view:'edit',model:[team:team])
            }
        }
        else {
            flash.message = "Team not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }
    
    def addGameResult = {

        if (params.winner.id == params.loser.id){
            flash.message = "Please choose different teams"
            return render(view:'updateLadder')
        }
            
    	def winner = Team.get(params.winner.id)
    	def loser = Team.get(params.loser.id)

    	winner.lastPlayed = Calendar.getInstance()
    	loser.lastPlayed = Calendar.getInstance()
    	
    	if (winner.rank > loser.rank){

    		def list = new LinkedList(Team.listOrderByRank())
    		list.remove(winner)
        	list.add(list.indexOf(loser), winner)

            reconcileTeamRanking(list)
    	}

    	reconcilePlayerRating()
    	
    	flash.message = "Game Result added."
    	render(view:'list')
        redirect(action:list)
    }
    
    def create = {
        def team = new Team()
        team.properties = params
        return ['team':team]
    }

    def save = {
        def team = new Team(params)
        team.rank = Team.list().size + 1
        if(!team.hasErrors() && team.save()) {
            flash.message = "Team ${team.id} created"
            redirect(action:show,id:team.id)
        }
        else {
            render(view:'create',model:[team:team])
        }
    }

    def playGame = { render(view:'choosePlayers') }
    
    def chooseTeamMembers = {
    	def players = params.findAll { it.key.startsWith("member") }.collect { Player.get( it.value ) }
    	
    	if (players.size() != 4) {
    		flash.message = "Select four different players!"
    		redirect(action:updateLadder)
    		return
    	}
    	
    	def arrangements = [[0, 1, 2, 3], [0, 2, 1, 3], [0, 3, 1, 2]]
    	
    	def teamPairings = []
    	arrangements.each {
    		def arrangement = it.collect { players[it] }
    		def teamA = Team.getTeam(arrangement[0], arrangement[1])
    		def teamB = Team.getTeam(arrangement[2], arrangement[3])

    		def pair = [teamA, teamB]
			if (teamA.rank == teamB.rank || teamA.rank || teamB.rank) {
				if (teamA.playerOne.rating + teamA.playerTwo.rating < teamB.playerOne.rating + teamB.playerTwo.rating) {
					pair = [teamB, teamA]
			 	}
			} else if (teamB.rank < teamA.rank) {
				pair = [teamB, teamA]
			}

			teamPairings << pair
    	}
    	
    	teamPairings = teamPairings.sort { a1, a2 ->
    		def diff = {
	    		def teamA = it[0]
	    		def teamB = it[1]
	    		return Math.abs((teamA.playerOne.rating + teamA.playerTwo.rating) - (teamB.playerOne.rating + teamB.playerTwo.rating))
    		}
    		
    		return diff(a1) - diff(a2)
    	}
    	
    	render(view:'enterResult', model:[teamPairings:teamPairings])
    }
    
    def gameResult = {
    	def priorRating = [:]
    	def priorRank = [:]
    	
		def winner = Team.getTeam(Player.get(params.winningTeam.player1), Player.get(params.winningTeam.player2))
		def loser = Team.getTeam(Player.get(params.losingTeam.player1), Player.get(params.losingTeam.player2))	
		
		winner.eachPlayer { player -> priorRating[player.id] = player.rating ; priorRank[player.id] = player.rank }
    	loser.eachPlayer { player -> priorRating[player.id] = player.rating ; priorRank[player.id] = player.rank }
		
		def aList = new LinkedList(Team.listOrderByRank())
		if (!aList.contains(winner)) aList.add(winner)
		if (!aList.contains(loser)) aList.add(loser)
		reconcileTeamRanking(aList)

    	winner.lastPlayed = Calendar.getInstance()
    	loser.lastPlayed = Calendar.getInstance()
    	
    	if (winner.rank > loser.rank){
    		aList.remove(winner)
        	aList.add(aList.indexOf(loser), winner)

            reconcileTeamRanking(aList)
    	}

    	reconcilePlayerRating()
    	
    	flash.message = """
    		<b><i>$winner.name</i></b>
    		beat
    		<i>$loser.name</i><br/>"""
    	
    	def evaluate = { player ->
    		flash.message += "$player.name is "
    		def rankChange = player.rank - priorRank[player.id]
    		if (rankChange == 0) {
	    		def diff = player.rating - priorRating[player.id]
	    		if (diff == 0) {
	    			flash.message += " no change"
	    		} else if (diff < 0) {
	    			flash.message += " up by <b>${-diff}</b> to $player.rating in their rating"
	    		} else {
	    			flash.message += " down by <b>${diff}</b> to $player.rating in their rating"
	    		}
    		} else if (rankChange < 0) {
    			flash.message += " up <b>${-rankChange}</b> places to rank <b>${player.rank}</b>"
    		} else {
    			flash.message += " down <b>${rankChange}</b> places to rank <b>${player.rank}</b>"
    		}
    		flash.message += "<br/>"
    	}
    		
    	winner.eachPlayer(evaluate) 
    	loser.eachPlayer(evaluate)
    	
    	render(view:'list')
        redirect(action:list)
    }
}

