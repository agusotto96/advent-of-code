import gleam/int
import gleam/list
import gleam/result
import gleam/string

pub fn depths(file: String) -> Result(List(Int), Nil) {
  file
  |> string.split("\n")
  |> list.map(int.parse)
  |> result.all
}

pub fn depth_increase(depths: List(Int)) -> Int {
  depths
  |> list.window_by_2
  |> list.filter(fn(w) { w.1 > w.0 })
  |> list.length
}

pub fn average_depths(depths: List(Int), window_size: Int) -> List(Int) {
  depths
  |> list.window(window_size)
  |> list.map(fn(w) { list.fold(w, 0, int.add) })
}
