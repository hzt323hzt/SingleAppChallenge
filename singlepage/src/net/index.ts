import {del, get, post, put} from "./http";
import {PaymentResult} from "../utils/model"

export function loadItems() {
    return post("/load_items",{},{});
}

export function result(input: PaymentResult) {
    return post("/result",{},input);
}
