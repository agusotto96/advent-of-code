package main

import (
	"testing"
)

func Test202501(t *testing.T) {
	tests := []struct {
		name     string
		password int
	}{
		{"part_1", 1135},
	}
	rotations, err := rotations("../inputs/202501.txt")
	if err != nil {
		t.Fatalf("failed to read rotations: %v", err)
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			password := password(rotations)
			if password != tt.password {
				t.Errorf("password = %d; want %d", password, tt.password)
			}
		})
	}
}
