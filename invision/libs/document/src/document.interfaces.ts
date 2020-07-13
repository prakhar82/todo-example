export interface Additions {
    [key: string]: any;
}
export interface Customizable {
    additions?: Additions;
}
export interface DocumentResponse extends Customizable {
    status: string;
    Documents: Documents;
}
export type Documents = Array<Document>;
export interface Document extends Customizable {
    name: string;
    type: string;
    date?: string;
    document: string;
}
