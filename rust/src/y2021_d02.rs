use std::{
    fs::File,
    io::{BufRead, BufReader},
};

pub enum Command {
    Forward(u8),
    Down(u8),
    Up(u8),
}

pub fn commands(path: &str) -> Result<Vec<Command>, Box<dyn std::error::Error>> {
    BufReader::new(File::open(path)?)
        .lines()
        .map(|line| {
            let line = line?;
            let [cmd_str, units_str]: [&str; 2] = line
                .split(' ')
                .collect::<Vec<_>>()
                .try_into()
                .map_err(|_| "expected exactly 2 parts")?;
            let units = units_str.parse::<u8>()?;
            let command = match cmd_str {
                "forward" => Command::Forward(units),
                "down" => Command::Down(units),
                "up" => Command::Up(units),
                other => return Err(format!("unknown command: {other}").into()),
            };
            Ok(command)
        })
        .collect()
}
