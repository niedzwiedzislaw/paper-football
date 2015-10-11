package ghx.football.domain.flow

import ghx.football.domain.structure.Location
import org.scalatest.words.MatcherWords
import org.scalatest.{FlatSpec, Matchers}

class PassTest extends FlatSpec with Matchers {

  "===" should "return false for inverted passes" in {
    val pass = Pass(Location(0, 0), Location(1, 0))
    val inverted = Pass(Location(1, 0), Location(0, 0))
    (inverted === pass) should be(false)
  }

  it should "return true for same passes" in {
    val pass = Pass(Location(0, 0), Location(1, 0))
    val inverted = Pass(Location(0, 0), Location(1, 0))
    (inverted === pass) should be(true)
  }
  
  "==" should "return true for inverted passes" in {
    val pass = Pass(Location(0, 0), Location(1, 0))
    val inverted = Pass(Location(1, 0), Location(0, 0))
    (inverted == pass) should be(true)
  }

  it should "return true for the same pass" in {
    val pass = Pass(Location(0, 0), Location(1, 0))
    val same = Pass(Location(0, 0), Location(1, 0))
    (same == pass) should be(true)
  }


}
