            
class PlayerController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ playerList: Player.listOrderByRank( params ) ]
    }

    def show = {
        def player = Player.get( params.id )

        if(!player) {
            flash.message = "Player not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ player : player, teamList:Team.findAllByPlayerOneOrPlayerTwo(player, player), ratingExplain:PlayerRatingStrategyController.playerRatingStrategy.explainRating(player) ] }
    }

    def delete = {
        def player = Player.get( params.id )
        if(player) {
            player.delete()
            player.refresh()
            new TeamController().reconcilePlayerRanking()
            flash.message = "Player ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Player not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def player = Player.get( params.id )

        if(!player) {
            flash.message = "Player not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ player : player ]
        }
    }

    def update = {
        def player = Player.get( params.id )
        if(player) {
            player.properties = params
            if(!player.hasErrors() && player.save()) {
                flash.message = "Player ${params.id} updated"
                redirect(action:show,id:player.id)
            }
            else {
                render(view:'edit',model:[player:player])
            }
        }
        else {
            flash.message = "Player not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def player = new Player()
        player.properties = params
        return ['player':player]
    }

    def save = {
        def player = new Player(params)
        player.rank = Player.list().size + 1    
        if(!player.hasErrors() && player.save()) {
            flash.message = "Player ${player.id} created"
            redirect(action:show,id:player.id)
        }
        else {
            render(view:'create',model:[player:player])
        }
    }
    
    def searchAJAX = {
    	def players = Player.findAllByNameIlike("%${params.query}%")
    	render(contentType: "text/xml") {
    		results() {
    			players.sort { it.name }.each { player ->
    				result() {
    					name(player.name.toLowerCase())
    				}
    			}
    		}
    	}
    } 
}