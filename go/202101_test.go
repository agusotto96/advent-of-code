package main

import (
	"testing"
)

func Test202101(t *testing.T) {
	tests := []struct {
		name       string
		windowSize int
		expected   int
	}{
		{"part_1", 1, 1655},
		{"part_2", 3, 1683},
	}
	depths, err := depths("../inputs/202101.txt")
	if err != nil {
		t.Fatalf("failed to read depths: %v", err)
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			depthIncrements := depthIncrements(depths, tt.windowSize)
			if depthIncrements != tt.expected {
				t.Errorf("depth increments = %d; want %d", depthIncrements, tt.expected)
			}
		})
	}
}
