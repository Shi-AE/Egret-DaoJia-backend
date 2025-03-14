-- 抢券lua实现
-- key: 库存(KEYS[1]), 抢券成功列表(KEYS[2]), 抢券同步队列(KEYS[3])
-- argv: 活动id(ARGV[1]), 用户id(ARGV[2]),  EdjCoupon实体(ARGV[3])

-- 校验优惠券是否已经抢过
local couponNum = redis.call("HGET", KEYS[2], ARGV[2])
if couponNum and tonumber(couponNum) >= 1 then
    return "-1"
end

-- 校验库存是否充足
local stockNum = redis.call("HGET", KEYS[1], ARGV[1])
if not stockNum or tonumber(stockNum) <= 0 then
    return "-2"
end

-- 减库存
local subStockNum = redis.call("HINCRBY", KEYS[1], ARGV[1], -1)
if not subStockNum or tonumber(subStockNum) < 0 then
    return "-3"
end

-- 写入抢券列表
local listNum = redis.call("HSET", KEYS[2], ARGV[2], ARGV[1])
if not listNum or tonumber(listNum) < 1 then
    return "-4"
end

-- 写入抢券结果同步队列
local result = redis.call("LPUSH", KEYS[3], ARGV[3])
if not result or tonumber(result) <= 0 then
    return "-5"
end

return ARGV[1]

--[[
 -1: 限领一张
 -2: 已抢光
 -4: 已抢光
 -3: 写入抢券成功队列失败，返回给用户为：抢券失败
 -5: 写入抢券同步队列失败，返回给用户为：抢券失败
--]]