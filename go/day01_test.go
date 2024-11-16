package main

import (
	"testing"
)

func TestDay01(t *testing.T) {
	depths, err := depths("../inputs/202101.txt")
	if err != nil {
		t.Fatalf("failed to read depths: %v", err)
	}
	depthIncrements := depthIncrements(depths)
	expected := 1655
	if depthIncrements != expected {
		t.Errorf("depth increments = %d; want %d", depthIncrements, expected)
	}
}
