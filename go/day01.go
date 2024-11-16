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

func depthIncrements(depths []int) int {
	var increments int
	for i := 1; i < len(depths); i++ {
		current := depths[i]
		last := depths[i-1]
		if current > last {
			increments++
		}
	}
	return increments
}
