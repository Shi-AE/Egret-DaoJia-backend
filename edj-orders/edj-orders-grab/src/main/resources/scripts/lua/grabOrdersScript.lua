-- 抢单lua实现
-- key: 库存(KEYS[1]), 抢券同步队列(KEYS[2])
-- argv: 订单id(ARGV[1]), 用户id(ARGV[2]), 是否机器抢单（1：机器抢单，0：人工抢单）(ARGV[3])

-- 校验库存是否充足
local stockNum = redis.call("HGET", KEYS[1], ARGV[1])
if not stockNum or tonumber(stockNum) <= 0 then
    return "-1"
end

-- 减库存
local subStockNum = redis.call("HINCRBY", KEYS[1], ARGV[1], -1)
if not subStockNum or tonumber(subStockNum) < 0 then
    return "-2"
end

-- 写入结果同步队列
local result = redis.call("HSETNX", KEYS[2], ARGV[1], "[" .. ARGV[2] .. "," .. ARGV[3] .. "]")
if not result or tonumber(result) <= 0 then
    return "-3"
end

return ARGV[1]

--[[
 -1: 已抢光
 -2: 已抢光
 -3: 写入抢券同步队列失败，返回给用户为：抢券失败
--]]