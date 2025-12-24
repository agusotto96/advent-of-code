package main

import (
	"bufio"
	"io"
	"math"
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

func password(dial int, countAllZeros bool, rotations []int) int {
	password := 0
	for _, rotation := range rotations {
		newDial := dial + rotation
		newDial = newDial % 100
		if newDial < 0 {
			newDial += 100
		}
		if newDial == 0 {
			password += 1
		}
		if countAllZeros {
			password += int(math.Abs(float64(rotation)) / 100)
			rotation = rotation % 100
			if newDial != 0 && dial != 0 {
				if rotation > 0 && newDial < dial {
					password += 1
				}
				if rotation < 0 && newDial > dial {
					password += 1
				}
			}
		}
		dial = newDial
	}
	return password
}
