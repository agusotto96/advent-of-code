package main

import (
	"io"
	"os"
	"strings"
	"testing"
)

func Test202501(t *testing.T) {
	file, err := os.Open("../inputs/202501.txt")
	if err != nil {
		t.Fatalf("failed to open file: %v", err)
	}
	defer file.Close()

	tests := []struct {
		name     string
		r        io.Reader
		password int
	}{
		{"example", strings.NewReader("L68\nL30\nR48\nL5\nR60\nL55\nL1\nL99\nR14\nL82\n"), 3},
		{"part_1", file, 1135},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			dial := 50
			rotations, err := rotations(tt.r)
			if err != nil {
				t.Fatalf("failed to read rotations: %v", err)
			}
			password := password(dial, rotations)
			if password != tt.password {
				t.Errorf("password = %d; want %d", password, tt.password)
			}
		})
	}
}
