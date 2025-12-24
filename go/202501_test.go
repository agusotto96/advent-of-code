package main

import (
	"bytes"
	"io"
	"os"
	"strings"
	"testing"
)

func Test202501(t *testing.T) {
	example := "L68\nL30\nR48\nL5\nR60\nL55\nL1\nL99\nR14\nL82\n"
	input, err := os.ReadFile("../inputs/202501.txt")
	if err != nil {
		t.Fatalf("failed to read input file: %v", err)
	}

	tests := []struct {
		name          string
		dial          int
		countAllZeros bool
		r             io.Reader
		password      int
	}{
		{
			name:          "example_1",
			dial:          50,
			countAllZeros: false,
			r:             strings.NewReader(example),
			password:      3,
		},
		{
			name:          "example_2",
			dial:          50,
			countAllZeros: true,
			r:             strings.NewReader(example),
			password:      6,
		},
		{
			name:          "part_1",
			dial:          50,
			countAllZeros: false,
			r:             bytes.NewReader(input),
			password:      1135,
		},
		{
			name:          "part_2",
			dial:          50,
			countAllZeros: true,
			r:             bytes.NewReader(input),
			password:      6558,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			rotations, err := rotations(tt.r)
			if err != nil {
				t.Fatalf("failed to read rotations: %v", err)
			}
			password := password(tt.dial, tt.countAllZeros, rotations)
			if password != tt.password {
				t.Errorf("password = %d; want %d", password, tt.password)
			}
		})
	}
}
