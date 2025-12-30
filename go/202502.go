package main

import (
	"fmt"
	"io"
	"strconv"
	"strings"
)

func idRanges(r io.Reader) ([][2]int, error) {
	data, err := io.ReadAll(r)
	if err != nil {
		return nil, err
	}
	text := strings.TrimSpace(string(data))
	parts := strings.Split(text, ",")
	ranges := make([][2]int, 0, len(parts))
	for _, p := range parts {
		se := strings.Split(p, "-")
		start, err1 := strconv.Atoi(se[0])
		end, err2 := strconv.Atoi(se[1])
		if err1 != nil || err2 != nil {
			return nil, fmt.Errorf("invalid numbers in range: %q", p)
		}
		ranges = append(ranges, [2]int{start, end})
	}
	return ranges, nil
}

func invalidIds(ranges [][2]int) int {
	invalidIds := 0
	for _, rg := range ranges {
		lower := rg[0]
		upper := rg[1]
		for id := lower; id <= upper; id++ {
			s := strconv.Itoa(id)
			if len(s)%2 != 0 {
				continue
			}
			half := len(s) / 2
			if s[:half] == s[half:] {
				invalidIds += id
			}
		}
	}
	return invalidIds
}
