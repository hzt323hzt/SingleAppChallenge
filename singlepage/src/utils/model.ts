export interface ItemTotal extends Item {
    quantity?: number;
    tax?: number;
    total?: number;
}

export interface Item{
    id?: number;
    price?: number;
}

export interface PaymentResult extends Customer{
    total?: number;
    items?: ItemTotal[];
}

export interface Customer{
    id?: number;
    name?: string;
    address?: string;
}