use rust::y2021_d01::depth_increments;
use rust::y2021_d01::depths;

#[test]
fn day_01_part_1() -> Result<(), Box<dyn std::error::Error>> {
    let depths = depths("../inputs/202101.txt")?;
    let depth_increments = depth_increments(depths, 1);
    assert_eq!(depth_increments, 1655);
    Ok(())
}

#[test]
fn day_01_part_2() -> Result<(), Box<dyn std::error::Error>> {
    let depths = depths("../inputs/202101.txt")?;
    let depth_increments = depth_increments(depths, 3);
    assert_eq!(depth_increments, 1683);
    Ok(())
}
