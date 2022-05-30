import { AccountOperation } from "./accountOperation.mdel";

export interface AccountHistory {
    accountId:               string;
    balance:                 number;
    currentPage:             number;
    totalPages:              number;
    pageSize:                number;
    accountOperationDTOList: AccountOperation[];
}

