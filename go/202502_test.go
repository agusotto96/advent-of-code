package main

import (
	"bytes"
	"io"
	"os"
	"strings"
	"testing"
)

func TestIdRanges(t *testing.T) {
	example := "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"
	input, err := os.ReadFile("../inputs/202502.txt")
	if err != nil {
		t.Fatalf("failed to read input file: %v", err)
	}

	tests := []struct {
		name        string
		r           io.Reader
		wantInvalid int
	}{
		{
			name:        "example",
			r:           strings.NewReader(example),
			wantInvalid: 1227775554,
		},
		{
			name:        "input",
			r:           bytes.NewReader(input),
			wantInvalid: 35367539282,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got, err := idRanges(tt.r)
			if err != nil {
				t.Fatalf("failed to parse ranges: %v", err)
			}
			gotInvalid := invalidIds(got)
			if gotInvalid != tt.wantInvalid {
				t.Errorf("invalidIds = %d; want %d", gotInvalid, tt.wantInvalid)
			}
		})
	}
}
