package main

import (
	"bufio"
	"io"
	"iter"
	"math"
	"strconv"
)

func rotations(r io.Reader) iter.Seq2[int, error] {
	return func(yield func(int, error) bool) {
		scanner := bufio.NewScanner(r)
		for scanner.Scan() {
			line := scanner.Text()
			rotation, err := strconv.Atoi(line[1:])
			if err != nil {
				if !yield(0, err) {
					return
				}
				continue
			}
			if line[0] == 'L' {
				rotation = -rotation
			}
			if !yield(rotation, nil) {
				return
			}
		}
		if err := scanner.Err(); err != nil {
			_ = yield(0, err)
		}
	}
}

func password(dial int, countAllZeros bool, rotations iter.Seq2[int, error]) (int, error) {
	password := 0
	for rotation, err := range rotations {
		if err != nil {
			return 0, err
		}
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
	return password, nil
}
