package main

import (
	"bufio"
	"io"
	"strconv"
)

func rotations(r io.Reader) ([]int, error) {
	var rotations []int

	scanner := bufio.NewScanner(r)
	for scanner.Scan() {
		line := scanner.Text()

		rotation, err := strconv.Atoi(line[1:])
		if err != nil {
			return nil, err
		}

		isLeft := line[0] == 'L'
		if isLeft {
			rotation = -rotation
		}

		rotations = append(rotations, rotation)
	}

	return rotations, scanner.Err()
}

func password(dial int, rotations []int) int {
	password := 0
	for _, rotation := range rotations {
		dial = dial + rotation
		if dial%100 == 0 {
			password += 1
		}
	}
	return password
}
