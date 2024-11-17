package main

import (
	"bufio"
	"os"
	"strconv"
)

func depths(path string) ([]int, error) {
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	defer file.Close()
	var depths []int
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		depth, err := strconv.Atoi(line)
		if err != nil {
			return nil, err
		}
		depths = append(depths, depth)
	}
	return depths, scanner.Err()
}

func depthIncrements(depths []int, windowSize int) int {
	var increments int
	var lastDepth int
	for i := 0; i <= len(depths)-windowSize; i++ {
		var depth int
		for _, d := range depths[i : i+windowSize] {
			depth += d
		}
		if i != 0 && depth > lastDepth {
			increments++
		}
		lastDepth = depth
	}
	return increments
}
