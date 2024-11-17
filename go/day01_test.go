package main

import (
	"testing"
)

func TestDepthIncrements(t *testing.T) {
	tests := []struct {
		name       string
		windowSize int
		expected   int
	}{
		{"Day01", 1, 1655},
		{"Day02", 3, 1683},
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
