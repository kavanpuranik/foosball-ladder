import grails.util.GrailsUtil

class BootStrap {

     def init = { 
             servletContext ->
             if ('development' == GrailsUtil.environment) { 
            	def john = new Player(name: 'John', rank: 1, rating:1.05).save()
              	def merlyn = new Player(name: 'Merlyn', rank: 2, rating:1.5).save()
             	def ben = new Player(name: 'Ben', rank: 3, rating:1.75).save()
             	def kavan = new Player(name: 'Kavan', rank: 4, rating:7.23).save()
//             	def mike = new Player(name: 'Mike', rank: 0).save()
//             	def peter = new Player(name: 'Peter', rank: 0).save()
//             	def bill = new Player(name: 'Bill', rank: 0).save()
//             	def dan = new Player(name: 'Dan', rank: 0).save()
//             	def nick = new Player(name: 'Nick', rank: 0).save()
//             	def rodel = new Player(name: 'Rodel', rank: 0).save()
             	
             	new Team(name: 'The Dynamics', playerOne: ben, playerTwo: merlyn, rank: 1, lastPlayed: Calendar.getInstance()).save()
             	new Team(name: 'Byte Me', playerOne: john, playerTwo: kavan, rank: 2, lastPlayed: Calendar.getInstance()).save()
             	new Team(name: 'The Foreigners', playerOne: kavan, playerTwo: merlyn, rank: 3, lastPlayed: Calendar.getInstance()).save()
//             	new Team(name: 'The Primitives', playerOne: bill, playerTwo: dan, rank: 4, lastPlayed: Calendar.getInstance()).save()
//             	new Team(name: 'The Statics', playerOne: dan, playerTwo: mike, rank: 5, lastPlayed: Calendar.getInstance()).save()
//             	new Team(name: 'The Transients', playerOne: nick, playerTwo: merlyn, rank: 6, lastPlayed: Calendar.getInstance()).save()
//             	new Team(name: 'Healthy Devs', playerOne: rodel, playerTwo: kavan, rank: 7, lastPlayed: Calendar.getInstance()).save()
             }
     }
     
     def destroy = {
     }
} 