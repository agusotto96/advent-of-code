use std::{fs::File, io::BufRead, io::BufReader};

pub fn depths(path: &str) -> Result<Vec<u128>, Box<dyn std::error::Error>> {
    File::open(path)
        .map(|file| BufReader::new(file))?
        .lines()
        .map(|line| Ok(line?.parse::<u128>()?))
        .collect()
}

pub fn depth_increments(depths: Vec<u128>, window_size: usize) -> usize {
    depths
        .windows(window_size)
        .map(|depths| depths.iter().sum())
        .collect::<Vec<u128>>()
        .windows(2)
        .filter(|depths| depths[1] > depths[0])
        .count()
}
