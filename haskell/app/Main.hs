module Main where

import Data.List (tails)
import Data.Function ((&))

main :: IO ()
main = do
    depths <- readDepths "../inputs/202101.txt"
    let result = depthIncrements depths 1 
    print result

readDepths :: FilePath -> IO [Int]
readDepths file = do
    content <- readFile file
    return $ map read (lines content)

depthIncrements :: [Int] -> Int -> Int
depthIncrements depths windowSize =
    depths
        & slidingWindow windowSize
        & map sum
        & slidingWindow 2
        & countIncrements
  where
    slidingWindow n xs = takeWhile ((== n) . length) $ map (take n) (tails xs)
    countIncrements xs = length $ filter (uncurry (<)) (zip xs (tail xs))
