export interface AccountOperation {
    id:            number;
    date:          Date;
    amount:        number;
    operationType: string;
    description:   string;
}
