class Player {
	String name
	Integer rank
	BigDecimal rating
	String toString() { "$name (${rank ?: ''}) " }
	
    static constraints = {
        rank(nullable: true)
        rating(nullable: true, scale:2)
    }
}
