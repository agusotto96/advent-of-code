package main

import (
	"bufio"
	"os"
	"strconv"
)

type Direction string

type rotation struct {
	isLeft   bool
	distance int
}

func rotations(path string) ([]rotation, error) {
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	defer file.Close()

	var rotations []rotation
	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		line := scanner.Text()
		isLeft := line[0] == 'L'
		distance, err := strconv.Atoi(line[1:])
		if err != nil {
			return nil, err
		}
		rotation := rotation{
			isLeft:   isLeft,
			distance: distance,
		}
		rotations = append(rotations, rotation)
	}

	return rotations, scanner.Err()
}

func password(rotations []rotation) int {
	password := 0
	dial := 50
	for _, rotation := range rotations {
		distance := rotation.distance
		if rotation.isLeft {
			distance = -distance
		}
		dial = dial + distance
		if dial%100 == 0 {
			password += 1
		}
	}
	return password
}
