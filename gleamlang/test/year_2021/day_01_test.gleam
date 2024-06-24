import gleeunit/should
import simplifile
import year_2021/day_01

pub fn part_1_test() {
  let assert Ok(file) = simplifile.read("../inputs/202101.txt")
  let assert Ok(depths) = day_01.depths(file)
  depths
  |> day_01.depth_increase
  |> should.equal(1655)
}

pub fn part_2_test() {
  let window_size = 3
  let assert Ok(file) = simplifile.read("../inputs/202101.txt")
  let assert Ok(depths) = day_01.depths(file)
  depths
  |> day_01.average_depths(window_size)
  |> day_01.depth_increase
  |> should.equal(1683)
}
