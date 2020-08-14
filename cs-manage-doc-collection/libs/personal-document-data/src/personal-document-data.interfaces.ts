export interface Additions {
    [key: string]: any;
}
export interface Customizable {
    additions: Additions;
}
export interface PersonalDocumentList extends Customizable {
    DocumentDataSummery: Array<PersonalDocumentListItem>;
    NextDocumentDataId: any;
}
export interface PersonalDocumentListItem extends Customizable {
    TemplateType: string;
    RMName: string;
    CategoryCode: string;
    Comments: string;
    OverallESignTransactionStatus: string;
    ESignTransactionDetails: EsignTransactionDetails;
    RMPid: string;
    PartyName: string;
    ESignStatus: string;
    DocumentTypeId: number;
    CategoryTypeId: number;
    Status: string;
    RequestMetadataId: number;
    ModifiedDate: string;
    CreateDate: string;
    FileNetDocumentCount: number;
    ParentRequestMetadataId: number;
    IsRead: boolean;
    DocumentType: string;
    DocumentCount: number;
    EsignMetadataId: number;
}
export interface EsignTransactionDetails extends Customizable {
    TotalUnsigned: number;
    TotalSigned: number;
    TotalNonConfirmedSignatures: number;
    TotalSigners: number;
    TotalDeclined: number;
}
export interface ICategories extends Customizable {
    CategoryCode: string;
    CategoryName: string;
    CategoryTypeId: any;
    checked: boolean;
    Types: Array<TypeItem>;
}
export interface ISelectedCategories {
    CategoryCode: string;
    CategoryName: string;
}
export type Categories = Array<{
    CategoryCode: string;
    CategoryName: string;
    CategoryTypeId: any;
    checked: boolean;
    Types: Array<TypeItem>;
}>;
export interface TypeItem extends Customizable {
    DocumentTypeId: any;
    DocumentTypeName: string;
}
export interface DocumentResponse extends Customizable {
    status: string;
    Documents: Documents;
}
export type Documents = Array<Document>;
export interface Document extends Customizable {
    name: string;
    type: string;
    date: string;
    document: string;
    category: string;
    read: boolean;
}
