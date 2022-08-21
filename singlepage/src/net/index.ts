import {del, get, post, put} from "./http";
import {PaymentResult} from "../utils/model"

export function loadItems() {
    return get("/load_items",{});
}

export function getResult(result: PaymentResult) {
    return post("/get_result",{},result);
}
